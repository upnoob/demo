package cn.cust.behavior;

/**
 * Created by upnoob on 2017/12/22.
 */
public class Test {
    public static void main(String[] args) {
        String str = "2017-07-20 13:58:57.835  INFO 17506 --- [http-nio-8080-exec-7] e.c.interceptor.UserBehaviorInterceptor  : 10209{-*-}xxadm,adm{-*-}10209{-*-}125.223.232.11{-*-}59435{-*-}125.223.232.11{-*-}/jyxm/adm/sbcheck/sbcheck_judgetimeAjax{-*-}http://210.47.0.215:8080/jyxm/adm/sbcheck/sbcheck_judgetimeAjax{-*-}40fc0a9c-5194-4672-8bb4-f7d847e0339d{-*-}edu.cust.demo.check.SbCheckAction$$EnhancerBySpringCGLIB$$9f73a5e4{-*-}judgeShjsTime{-*-}null{-*-}200{-*-}json{-*-}retMsg=审核结束\t{-*-}*/*{-*-}zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3{-*-}gzip, deflate{-*-}keep-alive{-*-}http://210.47.0.215:8080/jyxm/adm/sbcheck/list.jsp?xmlId=729&xmlmc=%25E9%25A1%25B9%25E7%259B%25AE%25E7%25B1%25BB%25E5%2590%258D%25E7%25A7%25B0%25EF%25BC%259A2017%25E5%25B9%25B4%25E8%25BD%25AC%25E5%259E%258B%25E8%25AF%2595%25E7%2582%25B9%25E9%25AB%2598%25E6%25A0%25A1%25E4%25B8%2593%25E9%25A1%25B9%25E8%25AF%25BE%25E9%25A2%2598{-*-}JSESSIONID=40fc0a9c-5194-4672-8bb4-f7d847e0339d{-*-}Mozilla/5.0 (Windows NT 6.1; rv:54.0) Gecko/20100101 Firefox/54.0";
        String[] ss = str.split("  : ");
        System.out.println(ss[0].substring(0, 23));
    }
}
