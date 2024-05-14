import com.google.inject.Inject;
import otus.components.BlockWithItemsComponents;
import otus.extensions.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import otus.pages.CoursesPage;
import otus.pages.MainPage;

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
    blockWithItemsComponents.moveToElement1();
    blockWithItemsComponents.clickRandomCategoryCourses();
    coursesPage.checkStateCheckboxCategory(blockWithItemsComponents.expectedTileCategory);
  }
}
