package bangkit.product.chickmed.activity

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import bangkit.product.chickmed.ui.screen.analysis.detail_analysis.DetailAnalysisScreen
import bangkit.product.chickmed.ui.theme.ChickMedTheme

class DetailAnalysisActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idReport = intent.getIntExtra("id_report", 0)

        setContent {
            ChickMedTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // A surface container using the 'background' color from the theme
                    val activity = (LocalContext.current as? Activity)
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(text = "Detail Analysis") },
                                navigationIcon = {
                                    IconButton(onClick = { activity?.finish() }) {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }
                                }

                            )
                        },
                    ) { paddingValue ->
                        DetailAnalysisScreen(id = idReport, modifier = Modifier.padding(paddingValue), redirectToWelcome = {
                            activity?.finish()
                        })
                    }
                }
            }
        }
    }
}