// IUserEntity.aidl
package com.ggg.custombinder;
import com.ggg.custombinder.bean.UserBean;

interface IUserEntity {

      void addUser(in UserBean entity);
      List<UserBean> getUsers();
}