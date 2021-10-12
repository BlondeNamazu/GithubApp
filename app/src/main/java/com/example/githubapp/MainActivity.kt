package com.example.githubapp

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
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
fun RepositoryList(repositories: List<GithubRepositoryEntity>) {
    Column {
        repositories.map {
            RepositoryItem(repository = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryItemPreview() {
    val repository = GithubRepositoryEntity(
        id = 0L,
        name = "Sample repository",
        ownerAvatarUrl = Uri.parse("https://avatars.githubusercontent.com/u/15728912"),
        description = "dummy description, this is sample preview",
        htmlUrl = Uri.EMPTY,
        apiUrl = Uri.EMPTY
    )
    RepositoryItem(repository)
}

@Composable
fun RepositoryItem(repository: GithubRepositoryEntity) {
    Row {
        Column {
            Image(
                painter = rememberImagePainter(repository.ownerAvatarUrl),
                contentDescription = null,
                modifier = Modifier.size(160.dp)
            )
            Text(
                text = repository.name,
                fontSize = 16.sp
            )
            Text(
                text = repository.description,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun ViewAndRefreshButton(repositories: List<GithubRepositoryEntity>, onClickListener: () -> Unit) {
    Column {
        Button(onClick = onClickListener) {
            Text("Update Repositories")
        }
        RepositoryList(repositories = repositories)
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