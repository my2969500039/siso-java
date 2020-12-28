package com.siso.repository.android.feedback;

import com.siso.entity.android.feedback.androidFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<androidFeedback,Long> {

    List<androidFeedback> findAllByNumber(String number);
}
