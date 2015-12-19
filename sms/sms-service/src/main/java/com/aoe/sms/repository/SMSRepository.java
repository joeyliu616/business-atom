package com.aoe.sms.repository;

import com.aoe.sms.entity.SMS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by joey on 15-12-18.
 */
@Repository
public interface SMSRepository extends JpaRepository<SMS,Long>{
}
