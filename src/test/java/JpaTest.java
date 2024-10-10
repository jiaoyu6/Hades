import com.haders.configs.ApplicationConfig;
import com.haders.pojo.QStaff;
import com.haders.pojo.Staff;
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
        Optional<Staff> byId = staffRepository.findById(1);
        System.out.println(byId.get());
    }

    @Test
    public void testSave(){
        Staff staff = new Staff();
        staff.setName("宇哥");
        staffRepository.save(staff);
    }

    @Test
    public void qtest(){
        QStaff qStaff = QStaff.staff;
        Staff staff = new Staff();
        staff.setName("宇哥");
        staffRepository.save(staff);
    }
}
