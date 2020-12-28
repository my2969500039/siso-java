package com.siso.repository.android.notice;


import com.siso.entity.android.noticet.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice,String> {

    List<Notice> findAllByType(String type);

    Notice findAllByTypeAndId(String type,Long id);


}
