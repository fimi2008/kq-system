package com.lionxxw.kqsystem.code.constants;

/**
 * <p>Description: 性别枚举 </p>
 *
 * @author lionxxw
 * @version 1.0
 * @time 16/5/6 下午4:44
 */
public enum SexEnum {
    SECRECY(0, "保密"),MAN(1, "男"),WOMAN(2,"女");
    private int index;
    private String value;
    SexEnum(int index, String value){
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**		
     * <p>Description: 根据index查询,该枚举的中文意思 </p>
     * 
     * @param index
     * @return String
     * @author lionxxw
     * @date 16/5/6 下午4:49
     * @version 1.0
     */
    public static String getSexByIndex(int index){
        SexEnum[] seies = SexEnum.values();
        for (SexEnum sex : seies){
            if (sex.getIndex() == index){
                return sex.getValue();
            }
        }
        return "";
    }
}
