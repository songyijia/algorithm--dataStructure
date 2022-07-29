package test;

import java.util.*;

import static algorithm.sort.MergeSort.process2;

public class ZijieTest1 {
    public static void main(String[] args) {
        int[] arr = {3,4,2,5,7,6,8,9,2,6,3,7,9};
        System.out.println(findArrayLength(arr,5));
//        int[] arr2 = {-1, 0, 1, 2, -1, -4};
        int[] arr2 = {-1,-2,-3,0,1,2,3};
        System.out.println(threeSum(arr2));
        int[] a1 = {2,5,6,0,0,0},a2={1,2,3};
        System.out.println(Arrays.toString(mergeArr(a1,3,a2,3)));
        int [] arr3 = {100, 80, 120, 130, 70, 60, 100, 125};
        System.out.println(getResult(arr3));
        System.out.println(maxProfit(arr3));
        int[] arr4 = {4,5,1,6,2,7,3,8};
        System.out.println(Arrays.toString(lessK(arr4,3)));
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;node2.next = node3;node3.next=node4;node4.next = node5;
//        ListNode head = reserveKGroupList(node1,2);
        ListNode head = reverseList(node1);
        while (head != null){
            System.out.print(head.val+" ,");
            head = head.next;
        }
        //判断二叉搜索树
        Node treeNode1 = new Node(1);
        Node treeNode2 = new Node(2);
        Node treeNode3 = new Node(4);
        Node treeNode4 = new Node(3);
        Node treeNode5 = new Node(5);
        treeNode3.left = treeNode2;
        treeNode3.right = treeNode5;
        treeNode2.left = treeNode1;
        treeNode2.right = treeNode4;
        System.out.println();
        /**
         * 判断是否为二叉搜索数
         *          3
         *       2       5
         *     1   4
         *
         */
        System.out.println("是否二叉搜索树"+checkSearchTree(treeNode3).isBST);
    }

    private static int[] lessK(int[] arr4, int k) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        for (int i = 0; i < arr4.length; i++) {
            minQueue.add(arr4[i]);
        }
        int[] res = new int[k];
        for (int i = 0; i < res.length; i++) {
            res[i] = minQueue.poll();
        }
        return res;
    }

    private static int findArrayLength(int[] arr, int n) {
        int length = 0;
        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>n){
                length++;
            }else {
                maxLength = maxLength>length?maxLength:length;
                length = 0;
            }
        }
        return maxLength>length ? maxLength:length;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        HashSet<List<Integer>> set = new HashSet<List<Integer>>();//set存放所有结果集
        for(int i = 0;i < nums.length; i++){
            HashMap<Integer,Integer> map = new HashMap<>();
            int target = 0 - nums[i];//target为两数之和
            for(int j = i+1; j < nums.length;j++){
                int value = target - nums[j]; //value为第三数
                if(map.containsKey(value)){
                    List<Integer> tmpList = new ArrayList<Integer>();
                    tmpList.add(nums[i]); //第一数
                    tmpList.add(nums[j]); //第二数
                    tmpList.add(value);   //第三数
                    Collections.sort(tmpList);
                    set.add(tmpList);

                }else{
                    map.put(nums[j],j);//缓存剩余元素，减少一次循环
                }

            }
        }
        return new ArrayList<List<Integer>>(set);
    }

    /**
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     说明:

     初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
     你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     输入:
     nums1 = [1,2,3,0,0,0], m = 3
     nums2 = [2,5,6],       n = 3

     输出: [1,2,2,3,5,6]

     * @param arr1
     * @param m
     * @param arr2
     * @param n
     * @return
     */
    public static int[] mergeArr(int[] arr1,int m,int[] arr2,int n){
        int idx = m+n-1, i=m-1,j=n-1;
        // 当 arr2 并入完成
        while (j >= 0) {
            if (i>=0 && arr1[i] >= arr2[j]) {
                arr1[idx] = arr1[i];
                i--;
            } else {
                arr1[idx] = arr2[j];
                j--;
            }
            idx--;
        }
        return arr1;
    }
    static class ListNode{
        int val;
        ListNode next;

        public ListNode(int value) {
            this.val = value;
        }
    }

    /**
     * 两个链表相加
     *      4->5->6
     *   +  3->6->0
     *  ==  8 ->1->6
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int index = 0;
        ListNode root = new ListNode(0);
        ListNode temp = root;
        while (l1 != null || l2 != null || index != 0) {  //这里加上index != 0 是为了解决当  l1 = {5},l2={5}的特殊情况。此时l1、l2都为null ,但是index = 1，需要进位。
            int l1val = l1!=null?l1.val:0;  //长度不一致时的情况。
            int l2val = l2!=null?l2.val:0;
            int add = l1val + l2val + index;
            if (add < 10)
            {
                temp.next = new ListNode(add);
                index =0;
            }
            else {
                temp.next = new ListNode(add % 10);
                index = 1;
            }
            temp = temp.next;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        return root.next;
    }

    /**
     * 给定一个数组代表股票每天的价格，请问只能买卖一次的情况下，最大化利润是多少？日期不重叠的情况下，可以买卖多次呢？

     输入: {100, 80, 120, 130, 70, 60, 100, 125}

     1）只能买一次：65（60 买进，125 卖出）

     2）可以买卖多次: 115（80买进，130卖出；60 买进，125卖出）
     */
    public static int maxProfit(int[] prices) {
        if(prices==null || prices.length==0) return 0;
        int profit = 0;
        int minPrice= prices[0];
        for(int i=1;i<prices.length;i++){
            //维护一个minPrice
            if(prices[i]<minPrice){
                minPrice = prices[i];
            }
            profit =  Math.max(profit,prices[i]-minPrice);
        }
        return profit;
    }



    public static int getResult(int[] arr){
        int maxG = 0;
        for (int i = 0; i < arr.length; i++) {
            maxG = Math.max(maxG,findMaxAfterN(arr,i+1)-arr[i]);
        }
        return maxG;
    }



    private static int findMaxAfterN(int[] arr,int i){
        int max = 0;
        for (int i1 = i; i1 < arr.length; i1++) {
            if (arr[i1]>max){
                max = arr[i1];
            }
        }
        return max;
    }

    /**最长无重复子串
     * 解题思路：使用两个指针模拟滑动窗口，让end指正不断的向后移动
     */
    public int lengthOfLongestSubstring(String s) {
        //利用hashmap来维护窗口内的字符无重复
        HashMap<Character,Integer> map = new HashMap<>();
        int res = 0;

        //定义一个start指针代表窗口的头部，end指针指向窗口的尾部
        int start = 0;
        for(int end=0;end<s.length();end++){
            if(map.containsKey(s.charAt(end))){
                //如果遇到重复元素，就让start指向map中该字符对应索引的下一个位置！！！
                start = Math.max(start,map.get(s.charAt(end))+1);
            }
            //键存放的end指向的值，值存放的是end这个索引 （跟sum3一样存元素下标）
            map.put(s.charAt(end),end);
            res = Math.max(res,end-start+1);
        }
        return res;
    }

    /**
     * 每k组反转链表
     * 1->2->3->4->5    ====> 2->1->4->3->5  k=2
     *                  ====> 3->2->1->4->5  k=3
     * @param head
     */
    public static ListNode reserveKGroupList(ListNode head,int k){
        ListNode p = head;

        int num = 0;

        while(p != null ){
            p = p.next;
            num++;
        }

        num /= k; //分组
        if(num == 0){
            return head;
        }

        ListNode curr = head;
        ListNode tail = curr;
        for(int i = 0;i < num;i++){ //每一组进行翻转
            ListNode newNode = null;
            ListNode newhead = curr;
            int count = k;
            while(count > 0){
                p = curr;
                curr = curr.next;
                p.next = newNode;
                newNode = p;
                count--;
            }
            if(i == 0){
                head = newNode;
            }else{
                tail.next = newNode;
                tail = newhead;
            }
        }

        while(curr != null){ //最后的尾
            tail.next = curr;
            tail = tail.next;
            curr = curr.next;
        }

        return head;

    }


    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null){
            return head;
        }
        int count = 0;
        ListNode curr = head;
        while(count != k && curr != null){
            curr = curr.next;
            count++;
        }
        if(count == k){ // 需要进行一次翻转，不等则保持不变。。。
            curr = reverseKGroup(curr,k); //翻转一次返回头节点
            while(count -- > 0){ //翻转
                ListNode tmp = head.next;
                head.next = curr; //当前头指向上次头
                curr = head;      //调换cur指针到当前头，当前头到next
                head = tmp;
            }
            head = curr;//翻转完，返回新头
        }
        return head;
    }

    /**
     * 用一个指针反转链表
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head){
        ListNode newHead = null;
        while (head != null){
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    static class Node{
        Node left;
        Node right;
        int value;
        Node(int v){
            this.value = v;
        }
    }
    static class Info{
        boolean isBST;
        int min;
        int max;
        Info(boolean isBST,int min,int max){
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
    /**
     * 判断是否为二叉搜索数
     *          3
     *       2       5
     *     1   4
     * @return
     */
    public static Info checkSearchTree(Node node){
        if (node == null){
            return new Info(false,0,0);
        }
        if (node.left == null && node.right == null){
            return new Info(true,node.value,node.value);
        }
        Info leftInfo=new Info(false,0,0),rightInfo = new Info(false,0,0);
        if (node.left != null){
            leftInfo = checkSearchTree(node.left);
        }
        if (node.right != null){
            rightInfo = checkSearchTree(node.right);
        }
        if (leftInfo.isBST && rightInfo.isBST && leftInfo.max < node.value && node.value < rightInfo.min){
            return new Info(true,leftInfo.min,rightInfo.max);
        }

        return new Info(false,0,0);
    }
}
