package com.example.githubapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.githubapp.ui.theme.GithubAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<GithubViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val repositories: List<GithubRepositoryEntity>
                            by viewModel.userRepositoryList.observeAsState(emptyList())
                    ViewAndRefreshButton(
                        repositories = repositories,
                        onClickListener = {
                            viewModel.getUserRepositoryList("BlondeNamazu")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ShowFirstRepositoryName(repositories: List<GithubRepositoryEntity>) {
    if (repositories.isEmpty()) Text(text = "Repository not found")
    else Text(text = repositories.first().name)
}

@Composable
fun ViewAndRefreshButton(repositories: List<GithubRepositoryEntity>, onClickListener: () -> Unit) {
    Column {
        ShowFirstRepositoryName(repositories = repositories)
        Button(onClick = onClickListener) {
            Text("Update Repositories")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GithubAppTheme {
        Greeting("Android")
    }
}