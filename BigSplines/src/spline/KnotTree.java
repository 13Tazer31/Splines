package spline;

import java.util.ArrayList;
import java.util.Arrays;

public class KnotTree {
	
	public static void main(String[] args) {
		float[] f = new float[99];
		for(int i = 0; i < 99; i++)
			f[i] = i;
		KnotTree tree = new KnotTree(f);
		tree.knots();
		//for(float fl : tree.knots())
			//System.out.println(fl);
		System.out.println(test.size());
		boolean correct = true;
		for(float i = 0; i < 99; i++)
			correct &= test.contains(i);
		System.out.println(correct);
	}
	
	private class Node {
		
		private Node(float value) {
			this.value = value;
		}
		
		private Node left, right;
		private float value;
	}
	
	public KnotTree(float[] knots) {
		initialiseTree(knots);
		size = knots.length;
	}
	
	private void initialiseTree(float[] knots) {
		Arrays.sort(knots);
		root = initNodes(0, knots.length-1, knots);
	}
	
	private Node initNodes(int lo, int hi, float[] knots) {
		if(lo > hi)
			return null;
		int center = (lo+hi)/2;
		Node newNode = new Node(knots[center]);
		newNode.left = initNodes(lo, center-1, knots);
		newNode.right = initNodes(center+1, hi, knots);
		return newNode;
	}
	
	private int size;
	
	private Node root;
	
	public float[] knots() {
		float[] knots = new float[size];
		extractNodes(0, size-1, root, knots);
		return knots;
	}
	
	private void extractNodes(int lo, int hi, Node node, float[] knots) {
		if(lo > hi)
			return;
		test.add(node.value);
		int center = (lo+hi)/2;
		knots[center] = node.value;
		extractNodes(lo, center-1, node.left, knots);
		extractNodes(center+1, hi, node.right, knots);
	}
	
	static ArrayList<Float> test = new ArrayList<Float>();

}
