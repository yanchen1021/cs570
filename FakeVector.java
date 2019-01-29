package HM1;

import java.lang.reflect.Array;
import java.util.Iterator;

public class FakeVector<T> implements Vector<T> {

    class MyIterator implements Iterator<T> {
	private int index = 0;

	@Override
	public boolean hasNext() {
	    return index != length;
	}

	@Override
	public T next() {
	    return data[index++];
	}
	@Override
		public void remove() {
	
		}
    }

    public static void main(String[] a) {
	FakeVector<Integer> test = new FakeVector();
	for (int i = 0; i < 10; i += 2) {
	    test.push(i);
	}
    for (Integer i : test) {
	    System.out.println(i);
	}
	for (int i = 1; i < 10; i += 2) {
	    test.insert(0, i);
	}
    for (Integer i : test) {
	    System.out.println(i);
	}
    test.set(0, 255);
    for (Integer i : test) {
	    System.out.println(i);
	}
    test.pop();
    for (Integer i : test) {
	    System.out.println(i);
	}
    }

    @SuppressWarnings("unchecked")
    private T[] data = (T[]) new Object[0];

    private int length = 0;

    @Override
    public T get(int index) {
	return data[index];
    }

    @Override
    public void insert(int index, T value) {
	length++;
	@SuppressWarnings("unchecked")
	T[] temp = (T[]) Array.newInstance(data.getClass().getComponentType(), length);
	for (int i = 0; i < index; i++) {
	    temp[i] = data[i];
	}
	temp[index] = value;
	for (int i = index; i < length - 1; i++) {
	    temp[i + 1] = data[i];
	}
	data = temp;
    }

    @Override
   public Iterator<T> iterator() {
	return new MyIterator();
    }
  
    @Override
    public T pop() {
	T lastOne = data[length - 1];
	length--;
	@SuppressWarnings("unchecked")
	T[] temp = (T[]) Array.newInstance(data.getClass().getComponentType(), length);
	for (int i = 0; i < length; i++) {
	    temp[i] = data[i];
	}
	data = temp;
	return lastOne;
    }

    @Override
    public void push(T value) {
	length++;
	@SuppressWarnings("unchecked")
	T[] temp = (T[]) Array.newInstance(data.getClass().getComponentType(), length);
	for (int i = 0; i < length - 1; i++) {
	    temp[i] = data[i];
	}
	temp[length - 1] = value;
	data = temp;

    }

    @Override
    public void set(int index, T value) {
	data[index] = value;

    }
}
