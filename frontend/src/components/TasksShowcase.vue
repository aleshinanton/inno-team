<template>
    <div class="taskShowCase">
        <the-navigation></the-navigation>
        <div class="filter">
            <form onsubmit="event.preventDefault();" role="search">
                <input id="search" type="search" placeholder="Введите ключевые слова" v-model="searchPhrase" autofocus />
                <button type="submit" @click="searchRequest()">Искать</button>
            </form>
        </div>
        <task-card task-name="Проблема" responsible="Ответственный" tags="Тэги" header="true"></task-card>
        <task-card v-for="item in listItems" :key="item.uuid" :task-id="item.uuid" :task-name="item.issue" :responsible="item.responsible" :initiator="item.initiator" :tags="item.tags"></task-card>
        <Pagination v-if="listItems" :total-pages="totalPages" :per-page="recordsPerPage" :current-page="page+1" @pagechanged="onPageChange" />
    </div>

</template>

<script>
import TheNavigation from './TheNavigation.vue'
import Pagination from './Pagination.vue'
import TaskCard from './TaskCard.vue'
import axios from 'axios'
export default {
    components: {
        TheNavigation,
        Pagination,
        TaskCard
    },
    data () {
        return {
            listItems: [],
            page: 0,
            totalPages: 0,
            totalRecords: 0,
            recordsPerPage: 20,
            enterpageno: '',
            searchPhrase: ''
        }
    },
    created () {
        this.loadListItem()
    },
    methods: {
        searchRequest() {
            if (this.searchPhrase) {
                axios.get(`/api/issue/search/fulltext?q=` + this.searchPhrase)
                    .then(response => {
                    console.log('responseS', response)
                    this.listItems = response.data.content
                    this.totalPages = response.data.totalPages
                    this.totalRecords = response.data.totalElements
                })
            } else {
                this.loadListItem()
            }
        },
        loadListItem () {
            axios.get(`/api/issue?page=${this.page}`)
                .then(response => {
                console.log('response', response)
                this.listItems = response.data.content
                this.totalPages = response.data.totalPages
                this.totalRecords = response.data.totalElements
            })
        },
        onPageChange (page) {
            this.page = page
            this.loadListItem()
        },
        onChangeRecordsPerPage () {
            this.loadListItem()
        },
        gotoPage () {
            if (!isNaN(parseInt(this.enterpageno))) {
                this.page = parseInt(this.enterpageno)
                this.loadListItem()
            }
        }
    }
}
</script>

<style scoped>
.taskShowCase {
    margin-top: 20px;
}
.filter {
    height: 50px;
    width: 75%;
    margin: 10px auto;
    padding: 15px;
}

form {
    border-bottom: 1px solid #009A96;
}

#search {
    width: 80%;
    border: none;
    outline: none;
    background-color: inherit;
    text-align: left;
    height: 30px;
    font-size: 15px;
}

button[type="submit"] {
    background: inherit;
    cursor: pointer;
    border: none;
    border-radius: 5px;
    height: 30px;
    font-size: 16px;
    width: 20%;
    color: #009A96;
}

button[type="submit"]:hover {
  box-shadow: 0 0 11px 2px rgba(33,33,33,.2);
    border: 1px solid #009A96;
}
</style>