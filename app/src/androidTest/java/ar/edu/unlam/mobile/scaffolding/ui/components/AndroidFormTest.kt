package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import ar.edu.unlam.mobile.scaffolding.domain.RealAndroid
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidFormTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun submitCallsOnSubmitWithEnteredValues() {
        // Given
        var submitted: RealAndroid? = null
        composeTestRule.setContent {
            AndroidForm(onSubmit = { submitted = it })
        }

        // When
        composeTestRule.onNodeWithText("Name").performTextInput("R2-D2")
        composeTestRule.onNodeWithText("Description").performTextInput("Astromech droid")
        composeTestRule.onNodeWithText("Image URL").performTextInput("https://example.com/r2d2.png")
        composeTestRule.onNodeWithText("Create").performClick()

        // Then
        assertEquals(
            RealAndroid(
                id = null,
                name = "R2-D2",
                description = "Astromech droid",
                picture = "https://example.com/r2d2.png",
            ),
            submitted,
        )
    }
}
