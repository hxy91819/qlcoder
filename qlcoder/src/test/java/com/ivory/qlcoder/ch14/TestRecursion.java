package com.ivory.qlcoder.ch14;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import com.ivory.qlcoder.util.FileUtil;
import com.ivory.qlcoder.vo.AllUserVo;

/**
 * 
 * 千里码第十四题，热点营销，递归解法
 * 
 * comments： 手写递归进行遍历，一开始不知道可以用图来处理。 处理100个用户的数据还可以，但是到了100000个用户数据，会直接报StackOverFlow
 *
 * @author huangxiangyu
 * @date: 2017年8月29日 下午9:02:45
 * @version 1.0
 * @since JDK 1.7
 */
public class TestRecursion extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<Integer>[] userArrayLists = new ArrayList[AllUserVo.USER_COUNT + 1];

    @Test
    public void testChildGraphByRecur() {
        String filenameString = "C:\\Users\\hxy\\Downloads\\weixinguanxi.txt";

        ArrayList<String> arrayList = FileUtil.readFileByLines(filenameString);

        int arrayListSize = arrayList.size();

        for (int i = 0; i < arrayListSize; i++) {
            String[] tempStrings = arrayList.get(i).split(" ");
            if (tempStrings.length == 0) {
                continue;
            }

            int userId = Integer.parseInt(tempStrings[0]);
            int userFriendId = Integer.parseInt(tempStrings[1]);

            if (userArrayLists[userId] == null) {
                userArrayLists[userId] = new ArrayList<Integer>();
            }

            if (userArrayLists[userFriendId] == null) {
                userArrayLists[userFriendId] = new ArrayList<Integer>();
            }

            // 用户是互相关注的，所以需要互相加入好友列表
            userArrayLists[userId].add(userFriendId);
            userArrayLists[userFriendId].add(userId);
        }

        AllUserVo allUserVo = new AllUserVo();
        // 找到第一个人的所有朋友
        recurFunc(allUserVo, 1);
        System.out.println("size: " + allUserVo.getUserCount() + ";List: " + allUserVo);
        
        // 题目给出的范例，遍历完第一个人，就得到总人数为100
    }

    /**
     * 
     * 递归方法
     * 
     * @author huangxiangyu
     * @date: 2017年8月29日 下午9:10:53
     * @version 1.0
     *
     * @param allUserVo
     * @param userId
     */
    private static void recurFunc(AllUserVo allUserVo, int userId) {
        if (!allUserVo.userAdd(userId)) {
            return;
        }
        // 逻辑是，获取指定用户Id的朋友列表，然后通过此方法，继续遍历这个朋友的朋友列表。
        ArrayList<Integer> friendsArrayList = userArrayLists[userId];
        for (int i = 0; i < friendsArrayList.size(); i++) {
            int friendId = friendsArrayList.get(i);
            System.out.println("useId: " + userId + ";friendId: " + friendId);
            System.out.println("size: " + allUserVo.getUserCount());
            if (allUserVo.isExist(friendId)) {
                continue;
            } else {
                recurFunc(allUserVo, friendId);
            }
        }
    }
}
