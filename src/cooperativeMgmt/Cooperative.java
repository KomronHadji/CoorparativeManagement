package cooperativeMgmt;

import java.util.*;

public class Cooperative {
    private List<String> members = new ArrayList<>();
    private Map<String, Integer> products = new HashMap<>();
    private Map<String, List<String>> campaigns = new HashMap<>();
    private Map<String, List<Integer>> payments = new HashMap<>();
    private Map<String, List<String>> enrolledCampaigns = new HashMap<>();
    private Map<String, Map<String, Integer>> orders = new HashMap<>();


    //R1
    public List<String> addMembers(String... names) {
        Set<String> insertedNames = new TreeSet<>();

        for (String name : names) {
            if (!members.contains(name) && !insertedNames.contains(name)) {
                members.add(name);
                insertedNames.add(name);
            }
        }

        return new ArrayList<>(insertedNames);
    }


    public List<String> addProducts(String... items) {
        List<String> productIds = new ArrayList<>();
        for (String item : items) {
            String[] parts = item.split(":");
            String productId = parts[0];
            int price = Integer.parseInt(parts[1]);
            products.put(productId, price);
            productIds.add(productId);
        }
        productIds.sort(String::compareTo);
        return productIds;
    }

    public List<String> addCampaign(String id, String... productIds) throws CMException {
        for (String productId : productIds) {
            if (!products.containsKey(productId)) {
                throw new CMException();
            }
        }
        List<String> productList = Arrays.asList(productIds);
        campaigns.put(id, productList);
        productList.sort(String::compareTo);
        return productList;
    }

    //R2
    public int join(String memberName, String campaignId) throws CMException {
        if (!members.contains(memberName) || !campaigns.containsKey(campaignId)) {
            throw new CMException();
        }

//        List<String> productList = campaigns.get(campaignId);
        List<String> enrolledList = enrolledCampaigns.get(campaignId);

        if (enrolledList == null) {
            enrolledList = new ArrayList<>();
            enrolledCampaigns.put(campaignId, enrolledList);
        }

        if (enrolledList.contains(memberName)) {
            return enrolledList.size();
        }

        enrolledList.add(memberName);
        return enrolledList.size();
    }

    public int addPayment(String memberName, int amount) throws CMException {
        if (!members.contains(memberName)) {
            throw new CMException();
        }

        List<Integer> paymentList = payments.getOrDefault(memberName, new ArrayList<>());
        paymentList.add(amount);
        payments.put(memberName, paymentList);

        int totalAmount = paymentList.stream().mapToInt(Integer::intValue).sum();
        return totalAmount;
    }

    public List<Integer> getPayments(String memberName) throws CMException {
        if (!members.contains(memberName)) {
            throw new CMException();
        }

        List<Integer> paymentList = payments.getOrDefault(memberName, new ArrayList<>());
        return paymentList;
    }

    //R3
    public int getBalance(String memberName) throws CMException {
        if (!members.contains(memberName)) {
            throw new CMException();
        }

        Map<String, Integer> memberOrders = orders.getOrDefault(memberName, new HashMap<>());
        int balance = 0;
        for (Map.Entry<String, Integer> entry : memberOrders.entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();
            int price = products.get(productId);
            balance -= quantity * price;
        }
        List<Integer> paymentList = payments.getOrDefault(memberName, new ArrayList<>());
        int totalAmount = paymentList.stream().mapToInt(Integer::intValue).sum();
        balance += totalAmount;

        return balance;
    }

    public int addOrder(String orderId, String memberName, String campaignId, String... items)
            throws CMException {

        return 0;
    }

    private int getOrdersTotalAmountForMember(String memberName) {
        return 0;
    }

    //R4
    public SortedMap<String, Integer> nOfUnitsPerProductFromOrders() {
        return new TreeMap<String, Integer>();
    }

    public SortedMap<String, Integer> amountPerMember() {
        return new TreeMap<String, Integer>();
    }
}
