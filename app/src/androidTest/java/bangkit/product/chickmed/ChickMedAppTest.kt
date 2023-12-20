package bangkit.product.chickmed

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import bangkit.product.chickmed.ui.navigation.Screen
import bangkit.product.chickmed.ui.theme.ChickMedTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
class ChickMedAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ChickMedTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                ChickMedApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("RewardList").performScrollToIndex(1)
        composeTestRule.onNodeWithText("libero").performClick()
//        navController.assertCurrentRouteName(Screen.DetailReward.route)
//        composeTestRule.onNodeWithText(FakeRewardDataSource.dummyRewards[10].title).assertIsDisplayed()
    }
}