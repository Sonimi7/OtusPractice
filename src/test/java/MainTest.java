import com.google.inject.Inject;
import components.BlockWithItemsComponents;
import extensions.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CoursesPage;
import pages.MainPage;

@ExtendWith(UIExtensions.class)
public class MainTest {

    @Inject
    private MainPage mainPage;

    @Inject
    private CoursesPage coursesPage;

    @Inject
    private BlockWithItemsComponents blockWithItemsComponents;

    @Test
    public void selectRightCategoryCourse() {
        mainPage.open();



        //blockWithItemsComponents.clickItem("Обучение");
    }
}
