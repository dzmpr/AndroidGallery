package pw.prsk.gallery.ui.home

import kotlinx.coroutines.*
import pw.prsk.gallery.data.NewsRepository

class NewsPresenter {
    private var view: NewsViewInterface? = null
    private val newsRepository = NewsRepository()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    fun attachView(view: NewsViewInterface) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun initNewsList() {
        view?.showToast("Start load articles")
        scope.launch {
            newsRepository.loadNews(10)
            view?.showProgressBar(false)
            view?.onDataUpdated(newsRepository.getNewsList())
        }
    }

    fun addNews() {

    }
}