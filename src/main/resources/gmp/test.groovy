package gmp

import com.yyl.domain.MyGroovyUser
import com.yyl.domain.MyUser

/**
 * @Author yang.yonglian* @ClassName: gmp* @Description: TODO(这里描述)
 * @Date 2019/7/25 0025
 */
mappers{
    mapper{
        from MyUser to MyGroovyUser converter {MyUser myUser,MyGroovyUser target ->
            target.age = myUser.age
            target.name = myUser.name
        }
    }
}
