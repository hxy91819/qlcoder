package com.ivory.qlcoder.vo;

/**
 *
 * 总用户类，用来保存已经遍历的用户<br/>
 *
 * @author huangxiangyu
 * @date: 2017年8月21日 下午10:31:23
 * @version 1.0
 * @since JDK 1.7
 */
public class AllUserVo {
    // 需要处理的用户量
    public static final int USER_COUNT = 100;
    
    private int[] users;
    
    private int sum = 0;
    
    public AllUserVo(){
        users = new int[USER_COUNT + 1];
    }
    
    public boolean isExist(int userId){
        int id=  users[userId];
        if(users[userId] == 1){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 
     * 添加用户<br/> 
     * 
     * @author huangxiangyu 
     * @date: 2017年8月21日 下午10:31:01
     * @version 1.0
     *
     * @param userId
     * @return
     */
    public boolean userAdd(int userId){
        if(users[userId] == 0){
            users[userId] = 1;
            sum++;
        } else {
            return false;
        }
        return true;
    }
    
    /**
     * 
     * 获取用户总数<br/> 
     * 
     * @author huangxiangyu 
     * @date: 2017年8月21日 下午10:31:10
     * @version 1.0
     *
     * @return
     */
    public int getUserCount(){
//        int sum = 0;
//        for(int i =1; i < USER_COUNT + 1; i++){
//            if(users[i] == 1){
//                sum ++;
//            }
//        }
        return sum;
    }
    
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for(int i =1; i < USER_COUNT + 1; i++){
            if(users[i] == 1){
                buffer.append(i).append(", ");
            }
        }
        return buffer.toString();
    }
}
