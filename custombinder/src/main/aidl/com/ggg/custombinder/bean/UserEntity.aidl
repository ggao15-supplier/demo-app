// UserEntity.aidl
package com.ggg.custombinder.bean;

// Declare any non-default types here with import statements
import com.ggg.custombinder.bean.UserEntity;
interface UserEntity {

      void addUser(in UserEntity entity);
      List<UserEntity> getUsers();
}