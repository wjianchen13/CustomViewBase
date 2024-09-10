package com.example.customviewbase.customview.marquee;


import com.example.customviewbase.customview.marquee.TextLinearDinoAdapter.TextLinearParseDinoResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 实现循环播放需求，初始数据100条
 * 定时播放，新插入的数据在播放数据之后，删除最旧的一条
 */
public class TextLinearDataManager {

    
    /**
     * 保持顺序数据列表
     * 记录显示数据的先后顺序，新数据从末端加入，移除第一个数据
     */
    private List<TextLinearDinoAdapter.TextLinearParseDinoResult> listDatas;

    /**
     * 显示数据
     */
    private List<TextLinearParseDinoResult> showDatas;

    /**
     * 当前播放数据的下标
     */
    private int playIndex;

    /**
     * 消息是否循环标志
     */
    private boolean isLoop;

    /**
     * 为防止容器过多，到达一定数量后不再添加
     */
    private static final int DINO_QUEUE_MAX_SIZE = 50;

    /**
     * 等待消息队列, 广播不循环
     */
    protected Queue<TextLinearParseDinoResult> dinoQueue;

    public TextLinearDataManager(boolean loop) {
        this.listDatas = new ArrayList<>();
        this.showDatas = new ArrayList<>();
        this.isLoop = loop;
        playIndex = 0;
    }

    /**
     * 添加首批数据，统一添加到数组末尾
     * @param datas
     */
    public void addData(List<TextLinearParseDinoResult> datas) {
        if(DinoObjectUtils.isEmpty(datas))
            return ;
        if (listDatas == null)
            listDatas = new ArrayList<>();
        listDatas.clear();
        listDatas.addAll(datas);
        if (showDatas == null)
            showDatas = new ArrayList<>();
        showDatas.clear();
        showDatas.addAll(datas);
        playIndex = 0;
    }

    /**`
     * 获取下一个播放的数据
     */
    public TextLinearParseDinoResult getItem() {
//        DinoLog.iTag("loopmessage", "==============> TextLinearDataManager getItem size: " + (showDatas != null ? showDatas.size() : -1)
//                + "  Loop Num: " +  LiveDinoShareData.getInstance().getBroadcastLoopNum());
        TextLinearParseDinoResult item = null;
        if(isLoop) {
            if (showDatas != null) {
                int size = showDatas.size();
                if (size > playIndex) {
                    item = showDatas.get(playIndex++);
                    item.setShow(0);
                    if (playIndex >= size) {
                        playIndex = 0;
                    }
                }
            }
        } else {
            item = dinoQueue != null ? dinoQueue.poll() : null;
        }
        return item;
    }

    public int size() {
        if(isLoop) {
            return showDatas != null ? showDatas.size() : 0;
        } else {
            return dinoQueue != null ? dinoQueue.size() : 0;
        }
    }
    
    public boolean isEmpty() {
        if(isLoop) {
            return DinoObjectUtils.isEmpty(showDatas);
        } else {
            return dinoQueue == null || dinoQueue.isEmpty();
        }
    }
    
    /**
     * 插入一个数据
     * @param item
     * @return
     */
    public void insertItem(TextLinearParseDinoResult item) {
        if(isLoop) {
            if (DinoObjectUtils.isEmpty(item))
                return;
            if (listDatas == null)
                listDatas = new ArrayList<>();
            if (showDatas == null)
                showDatas = new ArrayList<>();
            if (DinoObjectUtils.isNotEmpty(listDatas) && DinoObjectUtils.isNotEmpty(showDatas) && listDatas.size() >= 100) {
                item.setShow(1);
                TextLinearParseDinoResult delItem = DinoObjectUtils.isNotEmpty(listDatas) ? listDatas.get(0) : null;

                listDatas.remove(delItem);
                listDatas.add(item);

                int insertIndex = getInsertIndex(playIndex);
                showDatas.add(insertIndex, item);

                int deleteIndex = showDatas.indexOf(delItem);
                showDatas.remove(delItem);

                if (playIndex > 0 && playIndex > deleteIndex) {
                    playIndex--;
                }

            } else {
                listDatas.add(item);
                int insertIndex = getInsertIndex(playIndex);
                showDatas.add(insertIndex, item);
            }
        } else {
            if(dinoQueue == null)
                dinoQueue = new LinkedList<>();
            if(dinoQueue.size() < DINO_QUEUE_MAX_SIZE) {
                dinoQueue.offer(item);
            }
        }
//        DinoLog.iTag("loopmessage", "==============> TextLinearDataManager insertItem size: " + (showDatas != null ? showDatas.size() : -1)
//                + "  Loop Num: " +  LiveDinoShareData.getInstance().getBroadcastLoopNum());
    }
    
    public void clear(){
        if(isLoop) {
            clearCurrent();
        } else {
            if(dinoQueue != null)
                dinoQueue.clear();
        }
    }
    
    public void clearCurrent() {
        if(isLoop) {
            if (listDatas != null) {
                listDatas.clear();
                listDatas = null;
            }
            if (showDatas != null) {
                showDatas.clear();
                showDatas = null;
            }
            playIndex = 0;
        } else { 
            if(dinoQueue != null)
                dinoQueue.clear();
        }
    }

    /**
     * 获取插入数据的index，因为当前播放的index后面也可能有新插入的数据
     * 要在新插入的数据后面插入
     * @param playIndex 从当前index开始查找
     * @return
     */
    private int getInsertIndex(int playIndex) {
        int index = 0;
        if(DinoObjectUtils.isNotEmpty(showDatas)) {
            for (int i = playIndex; i < showDatas.size(); i ++) {
                if(showDatas.get(i).isShow()) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
    
}
