package com.qf.service;

import com.qf.pojo.Subject;
import com.qf.pojo.SubjectExample;

import java.util.List;

public interface SubjectService {
    List<Subject> findAllSubject();
    Subject fingById(Integer id);
    Subject findBySubjectId(Integer id);
}
