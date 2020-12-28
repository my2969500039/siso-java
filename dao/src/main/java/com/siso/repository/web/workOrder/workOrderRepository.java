package com.siso.repository.web.workOrder;

import com.siso.entity.web.feedback.adminFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface workOrderRepository extends JpaRepository<adminFeedback,String> {

    //获取所有工单
//    @Select("select * from admin_feedback")
//    List<adminFeedback> get_word();

//    //安卓提交反馈
//    @Insert("insert into android_feedback(id,content,img1,img2,img3,number,type) values(null,#{content},#{img1},#{img2},#{img3},#{number},#{type})")
//    public Boolean post_android_feedback(@Param("content") String content, @Param("img1") String img1,
//                                         @Param("img2") String img2, @Param("img3") String img3, @Param("number") String number,
//                                         @Param("type") String type);

    //安卓用户获取反馈
//    @Select("select * from android_feedback where number=#{number}")
//   List<adminFeedback> findAllBy(@Param("number") String numnber);
}
