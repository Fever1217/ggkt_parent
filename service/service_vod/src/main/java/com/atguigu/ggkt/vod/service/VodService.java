package com.atguigu.ggkt.vod.service;

public interface VodService {
    //上传视频
    String updateVideo();
    //删除视频
    void removeVideo(String fileId);
    //获取视频播放凭证
    Object getPlayAuth(Long courseId, Long videoId);
}
