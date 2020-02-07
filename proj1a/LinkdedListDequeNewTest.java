import org.junit.Test;
import static org.junit.Assert.*;

public class LinkdedListDequeNewTest {

    // 测试带参数的构造函数
    @Test
    public void TestLinkedListDeque(){
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        lld1.addFirst("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        LinkedListDeque<String> lld2 = new LinkedListDeque<>(lld1);

        System.out.println("暂时不写比较是否相等的功能");
    }

}
