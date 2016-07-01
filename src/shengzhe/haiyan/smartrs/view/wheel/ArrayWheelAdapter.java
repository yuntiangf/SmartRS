package shengzhe.haiyan.smartrs.view.wheel;

import java.util.List;

public class ArrayWheelAdapter<T> implements WheelAdapter {

	/** The default items length */
	public static final int DEFAULT_LENGTH = -1;

	// items
	private List<T> items;
	// length
	private int length;

	/**
	 * Constructor
	 * 
	 * @param items
	 *            the items
	 * @param length
	 *            the max items length
	 */
	public ArrayWheelAdapter(List<T> items, int length) {
		this.items = items;
		this.length = length;
	}

	/**
	 * Contructor
	 * 
	 * @param items
	 *            the items
	 */
	public ArrayWheelAdapter(List<T> items) {
		this(items, DEFAULT_LENGTH);
	}

	@Override
	public String getItem(int index) {
		if (index >= 0 && index < items.size()) {
			if (items.get(index).toString().length() > 4) {
				return items.get(index).toString().substring(0, 4);
			}
			return items.get(index).toString();
		}
		return null;
	}

	@Override
	public int getItemsCount() {
		return items.size();
	}

	@Override
	public int getMaximumLength() {
		return length;
	}
}
