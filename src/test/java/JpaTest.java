import com.haders.configs.ApplicationConfig;
import com.haders.entity.StaffEntity;
import com.haders.repositories.StaffRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

//classes 指定配置类
@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaTest {
    @Autowired
    StaffRepository staffRepository;

    @Test
    public void testR(){
        Optional<StaffEntity> byId = staffRepository.findById(1);
        System.out.println(byId.get());
    }

    @Test
    public void testSave(){
        StaffEntity staff = new StaffEntity();
        staff.setName("宇哥");
        staffRepository.save(staff);
    }

    @Test
    public void qtest(){
        StaffEntity staff = new StaffEntity();
        staff.setName("宇哥");
        staffRepository.save(staff);
    }
}
