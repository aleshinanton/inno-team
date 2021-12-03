<template>
    <div class="showcase">
        <div class="links">
            <h3>Реестр решений</h3>
            <h3>Реестр задач</h3>
        </div>
        <project-card v-for="item in listItems" :key="item.id" :project-name="item.project.name" :project-id="item.id" :organisation="item.project.orgName" :project-status="item.stage"></project-card>
        <Pagination v-if="listItems" :total-pages="totalPages" :per-page="recordsPerPage" :current-page="page" @pagechanged="onPageChange" />
    </div>
</template>

<script>
import ProjectCard from './ProjectCard.vue'
import Pagination from './Pagination'
import { $axios } from '@baloise/vue-axios'

// import { getTestData } from '../services/test'

export default {
    components: {
        ProjectCard,
        Pagination
    },
    data () {
        return {
            listItems: [],
            page: 0,
            totalPages: 0,
            totalRecords: 0,
            recordsPerPage: 20,
            enterpageno: ''
        }
    },
    created () {
        this.loadListItem()
    },
    methods: {
        loadListItem () {
            $axios.get(`/api/application?page=${this.page}`)
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
.showcase {
    margin-top: 20px;
}

.links {
    display: flex;
}

h3 {
    text-align: left;
    padding: 0 15px;
    color: #009A96;
    cursor: pointer;
}

h3:last-child {
    border-left: 2px solid #009A96;
}
</style>