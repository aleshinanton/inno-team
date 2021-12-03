<template>
    <div class="taskShowCase">
        <the-navigation></the-navigation>
        <task-card task-name="Проблема" responsible="Ответственный" initiator="Инициатор" header="true"></task-card>
        <task-card v-for="item in listItems" :key="item.uuid" :task-name="item.issue" :responsible="item.responsible" :initiator="item.initiator"></task-card>
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
            axios.get(`/api/issue/search/fulltext?q=` + this.searchPhrase)
                .then(response => {
                console.log('responseS', response)
                this.listItems = response.data.content
                this.totalPages = response.data.totalPages
                this.totalRecords = response.data.totalElements
            })
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

<style>
.taskShowCase {
    margin-top: 20px;
}
</style>