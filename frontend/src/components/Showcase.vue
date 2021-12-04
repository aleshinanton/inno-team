<template>
    <div class="showcase">
        <the-navigation></the-navigation>
        <div class="filter">
            <form onsubmit="event.preventDefault();" role="search">
                <input id="search" type="search" placeholder="Введите ключевые слова" v-model="searchPhrase" autofocus required />
                <button type="submit" @click="searchRequest()" :disabled="!searchPhrase">Искать</button>
            </form>
        </div>
      <project-card project-name="Наименование проекта" project-id="№" organisation="Организация" project-status="Статус проекта" header="true"></project-card>
        <project-card v-for="item in listItems" :key="item.id" :project-name="item.project.name" :project-id="item.id" :organisation="item.project.orgName" :project-status="item.stage" :header="false"></project-card>
        <Pagination v-if="listItems" :total-pages="totalPages" :per-page="recordsPerPage" :current-page="page+1" @pagechanged="onPageChange" />
    </div>
</template>

<script>
import ProjectCard from './ProjectCard.vue'
import Pagination from './Pagination.vue'
import TheNavigation from './TheNavigation.vue'
import { $axios } from '@baloise/vue-axios'
//import TheFilters from './TheFilters.vue'
import axios from 'axios'
export default {
    components: {
        ProjectCard,
        Pagination,
        TheNavigation,
      //  TheFilters
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
                axios.get(`/api/application/search/fulltext?q=` + this.searchPhrase)
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
            $axios.get(`/api/application?page=${this.page}&size=10`)
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
.showcase {
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