package cl.bootcamp.ind1m6android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import cl.bootcamp.ind1m6android.ui.theme.Ind1M6AndroidTheme
import cl.bootcamp.ind1m6android.viewModel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        val contactViewModel: ContactViewModel by viewModels()
        setContent {
            Ind1M6AndroidTheme {

            }

        }
    }
}
