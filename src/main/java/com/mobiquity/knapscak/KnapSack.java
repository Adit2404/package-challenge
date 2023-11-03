package com.mobiquity.knapscak;

import com.mobiquity.model.PackageCalculation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mobiquity.model.Item;

import java.util.*;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

import com.mobiquity.model.Package;
import static com.mobiquity.constant.PackerConstant.NEW_LINE;

public class KnapSack {

    private static final Logger logger = LoggerFactory.getLogger(KnapSack.class);

    private final StampedLock lock = new StampedLock();
    private final Map<Package, Queue<Item>> packageItems = new LinkedHashMap<>();
    private final List<PackageCalculation> packageCalculations = new ArrayList<>();

    // Singleton instance
    public static KnapSack getInstance() {
        return KnapSack.InstanceHolder.INSTANCE;
    }

    private KnapSack() {
    }

    // Method to calculate and store items for each package

    public KnapSack calculate(List<Package> packages) {
        long writeStamp = lock.writeLock();
        logger.debug("Acquiring write lock {}", writeStamp);
        try {
            for (Package pack : packages) {
                Queue<Item> itemQueue = createItemQueue();
                itemQueue.addAll(pack.getItems());
                packageItems.put(pack, itemQueue);
            }

            Set<Package> packageKeys = packageItems.keySet();
            for (Package pack : packageKeys) {
                packageCalculations.add(new PackageCalculation(pack.getID(), getValidItems(pack)));
            }
            logger.debug("Calculated package calculations {}", packageCalculations);
        } finally {
            lock.unlockWrite(writeStamp);
            logger.debug("Unlocking write lock {}", writeStamp);
        }
        return getInstance();
    }

    // Method to retrieve valid items within weight limits for a given package
    private List<Item> getValidItems(Package pack) {
        logger.debug("Getting all valid items for package {}", pack.getID());
        double weightLimit = pack.getWeightLimit();
        List<Item> validItems = new ArrayList<>();
        Queue<Item> itemQueue = packageItems.get(pack);
        // Logging for obtaining valid items for a package
        // Logic to find items within weight limit
        logger.debug("Calculating all items within the weight limit {}", weightLimit);
        while (!itemQueue.isEmpty() && weightLimit > 0) {
            Item item = itemQueue.poll();
            if (weightLimit >= item.getWeight()) {
                validItems.add(item);
                weightLimit -= item.getWeight();
            }
        }
        return validItems;
    }

    public String getCalculationsAsString() {
        // Attempt optimistic reading of the lock
        long optimisticRead = lock.tryOptimisticRead();
        logger.debug("Acquiring optimistic read lock {}", optimisticRead);
        if (lock.validate(optimisticRead)) {
            return convertCalculationsToString();
        }
        // Perform read lock and get calculations as a string
        // Release read lock
        optimisticRead = lock.readLock();
        try {
            return convertCalculationsToString();
        } finally {

            lock.unlockRead(optimisticRead);
            logger.debug("Unlocking optimistic read lock {}", optimisticRead);
        }
    }

    private String convertCalculationsToString() {
        logger.debug("Converting package calculations to string");

        return packageCalculations.stream()
                .map(PackageCalculation::toString)
                .collect(Collectors.joining(NEW_LINE));
    }

    private Queue<Item> createItemQueue() {
        logger.debug("Setting up customized item queue with cost-based ordering");
        return new PriorityQueue<>(10, (p1, p2) -> {
            int costComparison = Double.compare(p2.getCost(), p1.getCost());
            if (costComparison == 0) {
                return Double.compare(p1.getWeight(), p2.getWeight());
            } else {
                return costComparison > 0 ? 1 : -1;
            }
        });
    }

    // Method to clear KnapSack data
    public void clear() {
        long writeStamp = lock.writeLock();
        logger.debug("Acquiring write lock {}", writeStamp);
        try {
            packageItems.clear();
            packageCalculations.clear();
            logger.debug("Clearing KnapSack data");
        } finally {
            lock.unlockWrite(writeStamp);
            logger.debug("Unlocking write lock {}", writeStamp);
        }
    }

    private static class InstanceHolder {
        private static final KnapSack INSTANCE = new KnapSack();
    }
}
