package com.atguigu.ggkt.vod.service.impl;



import com.alibaba.excel.EasyExcel;
import com.atguigu.ggkt.exception.GgktException;
import com.atguigu.ggkt.model.vod.Subject;


import com.atguigu.ggkt.vo.vod.SubjectEeVo;
import com.atguigu.ggkt.vod.config.listener.SubjectLinstener;
import com.atguigu.ggkt.vod.mapper.SubjectMapper;
import com.atguigu.ggkt.vod.service.SubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-10-06
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    @Autowired
    SubjectLinstener subjectLinstener;
    //课程分类列表
    //懒加载 每次查询一层数据
    @Override
    public List<Subject> selectSubjectList(Long id) {

        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<Subject> subjectList = baseMapper.selectList(wrapper);

        // subjectList 遍历 得到每个subject对象 判断是否有下一层数据 有haschildren=true
        for (Subject subject : subjectList) {
            //获取subject的id值
            Long subjectId = subject.getId();
            //查询
            boolean isChild =  this.isChildren(subjectId);
            //封装到对象
            subject.setHasChildren(isChild);
        }
        return subjectList;
    }

    @Override
    public void exprotData(HttpServletResponse response) {

        try {
            //设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");

            //查询课程分类表里的所有数据
            List<Subject> subjectList = baseMapper.selectList(null);
            List<SubjectEeVo> subjectEeVoList = new ArrayList<>();
            for (Subject subject:subjectList) {
                SubjectEeVo subjectEeVo = new SubjectEeVo();
                BeanUtils.copyProperties(subject,subjectEeVo);
//                subjectEeVo.setId(subject.getId());
//                subjectEeVo.setParentId(subject.getParentId());
                subjectEeVoList.add(subjectEeVo);
            }
            //EasyExcel写操作
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class)
                    .sheet("课程分类")
                    .doWrite(subjectEeVoList);

        } catch (Exception e) {
           throw new GgktException(20001,"导出失败");
        }
    }

    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),SubjectEeVo.class,
                   subjectLinstener ).sheet().doRead();
        } catch (IOException e) {
            throw new GgktException(20001,"导入失败");
        }
    }

    //判断是否有下一层数据
    private boolean isChildren(Long subjectId) {
        QueryWrapper<Subject>wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",subjectId);
        Integer count = baseMapper.selectCount(wrapper);
        //1>0 true 0>0  false
        return count>0;
    }
}
