package cn.claycoffee.ClayTech.utils;

import java.util.List;

/**
 * @Project: ClayAPI
 * @Author: ClayCoffee
 * @Date: 2020/8/13 23:07
 * @Email: 1020757140@qq.com
 * AGPL 3.0
 */

public class ListUtils {
    public static boolean existsInStringList(List<String> list, String content) {
        return list.indexOf(content) != -1 ? true : false;
    }

    public static boolean existsInArray(int[] arr, int e) {
        for (int i : arr) {
            if (i == e) return true;
        }
        return false;
    }
}
