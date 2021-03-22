package com.siso.entity.android.noticet;

import com.siso.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "notice")
@Table(name = "notice")
public class Notice extends BaseEntity {


    private String userId;

    private String image;

    private String content;

    private String title;

    private String type;
}
