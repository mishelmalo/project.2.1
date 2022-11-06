package new_proj.project2.job;

import new_proj.project2.beans.Coupon;
import new_proj.project2.repository.CouponRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service

public class CouponExpirationDailyJob implements Runnable{

     CouponRepository couponRepository;


    public CouponExpirationDailyJob(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }
    private boolean quite=false;


    public void stop(){
        quite=true;
    }
    @Override
    public void run() {
//while the tread running delete coupon that expired!
        while (!quite) {
            try {
                List<Coupon> coupons = couponRepository.findAll();
                Calendar now = Calendar.getInstance();
                 //System.out.println(coupons);

                for (Coupon coupon : coupons) {
                    if (now.getTimeInMillis() > coupon.getEndDate().getTime()) {
                        couponRepository.deleteCouponsHistory(coupon.getId());
                        couponRepository.deleteById(coupon.getId());
                        //  System.out.println(" coupon deleted");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(24 * 60 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
