package com.simple.test;

import org.junit.Test;

public class TestSort {

	public TestSort() {
	}

	// 冒泡排序
	public void bubbleSort() {
		/*
		 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
		 */
		int[] nums = { 1, 7, 3, 9, 5, 2, 7 };
		for (int x = nums.length - 1; x > 0; x--) {
			for (int y = 0; y < x; y++) {
				int temp = 0;
				if (nums[y] > nums[y + 1]) {
					temp = nums[y];
					nums[y] = nums[y + 1];
					nums[y + 1] = temp;
				}
			}
		}
	}

	// 选择排序
	public void selectSort() {
		/*
		 * 第一个元素和第二个比较，较小值放第一个位置 ，第一个元素和第三个元素比较，较小值放第一个位置，第一个元素和第四个元素比较 ，较小值 放第一个位置。。。。
		 * ，第一轮下来，最小值放在了第一个位置，第二轮，让第二个元素和后面的元素逐个比较，剩下中的最小值放在了第二个位置。。。。
		 */
		int[] nums = { 1, 7, 3, 9, 5, 2, 7 };
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int temp = 0;
				if (nums[i] > nums[j]) {
					temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
		}
	}

	// 插入排序
	@Test
	/*
	 * 插入排序基本思想 将n个元素的数列分为已有序和无序两个部分： 每次处理就是将无序数列的第一个元素与有序数列的元素从后往前逐个进行比较，
	 * 找出插入位置，将该元素插入到有序数列的合适位置中。
	 */
	public void insertSort() {
		int[] nums = { 1, 7, 3, 9, 5, 2, 7 };
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				int temp = 0;
				if (nums[i] < nums[j]) {
					temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}

			}
		}
		for (int i : nums) {
			System.out.println(i);
		}
	}

	public void mergeSort() {

	}
}
