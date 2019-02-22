package com.example.yaohao.testproject.widget.sideBar;

import com.example.yaohao.testproject.mvp.pinpailist.PinPaiEntity;

import java.util.Comparator;

/**
 * 
 * @author xiaanming
 *
 */
public class PinyinPinPaiComparator implements Comparator<PinPaiEntity> {

	public int compare(PinPaiEntity o1, PinPaiEntity o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
