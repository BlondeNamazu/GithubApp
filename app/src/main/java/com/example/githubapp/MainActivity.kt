package com.example.githubapp

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
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
                        onClickListener = { userName: String ->
                            viewModel.getUserRepositoryList(userName)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryItemPreview() {
    val repository = GithubRepositoryEntity(
        id = 0L,
        name = "Sample repository",
        ownerAvatarUrl = Uri.parse("https://avatars.githubusercontent.com/u/15728912?v=4"),
        description = "dummy description, this is sample preview. dummy description, this is sample preview. dummy description, this is sample preview. dummy description, this is sample preview. dummy description, this is sample preview.dummy description, this is sample preview.  dummy description, this is sample preview. ",
        htmlUrl = Uri.EMPTY,
        apiUrl = Uri.EMPTY
    )
    RepositoryItem(repository)
}

@Composable
fun RepositoryItem(repository: GithubRepositoryEntity) {
    Row(
        modifier = Modifier.height(160.dp)
    ) {
        Image(
            painter = rememberImagePainter(repository.ownerAvatarUrl),
            contentDescription = null,
            modifier = Modifier.size(160.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(bottom = 12.dp)
        ) {
            Text(
                text = repository.name,
                modifier = Modifier
                    .offset(x = 8.dp, y = 12.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 24.sp
            )
            Text(
                text = repository.description,
                modifier = Modifier
                    .offset(x = 8.dp, y = 16.dp)
                    .fillMaxWidth(),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun ViewAndRefreshButton(
    repositories: List<GithubRepositoryEntity>,
    onClickListener: (userName: String) -> Unit
) {
    LazyColumn {
        item {
            InputField(onClickListener = onClickListener)
        }
        items(repositories) { repository ->
            RepositoryItem(repository = repository)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputFieldPreview() {
    InputField(onClickListener = { })
}

@Composable
fun InputField(
    onClickListener: (userName: String) -> Unit
) {
    Row {
        val userName = remember { mutableStateOf("") }
        TextField(
            value = userName.value,
            label = { Text("User Name") },
            onValueChange = { newName ->
                userName.value = newName
            })
        Button(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(all = 8.dp),
            onClick = { onClickListener(userName.value) }
        ) {
            Text("Search")
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