<template>
    <div class="project">
        <p class="breadcrumbs"><a href="/projects">Реестр решений</a> &#8594; {{projectId}}</p>
        <div class="itemField"><span class="fieldName">Наименование проекта:</span> {{item.project.name}}</div>
        <div class="itemField"><span class="fieldName">Организация транспортного комплекса Москвы:</span> {{item.project.orgName}}</div>
        <div class="itemField"><span class="fieldName">Участник программы пилотирования «Транспортные инновации Москвы»:</span> {{item.project.participant}}</div>
        <div class="itemField"><span class="fieldName">Руководитель проекта:</span> {{item.project.projectManager}}</div>
        <div class="itemField"><span class="fieldName">Координатор от участника программы пилотирования «Транспортные инновации Москвы»:</span> {{item.project.programCoordinator}}</div>
        <div class="itemField"><span class="fieldName">Координатор от организации транспортного комплекса Москвы:</span> {{item.project.orgCoordinator}}</div>
        <div class="itemField"><span class="fieldName">Краткое описание продукта:</span> {{item.project.description}}</div>
        <div class="itemField"><span class="fieldName">Сроки реализации проекта:</span> {{item.project.term}}</div>
        <div class="itemField"><span class="fieldName">Контекст и потребности:</span> {{item.project.context}}</div> 
    </div>
</template>

<script>
import axios from 'axios'
export default {
    props: ['projectId'],
    data () {
        return {
            item: {}
        }
    },
    created () {
        this.loadListItem()
    },
    methods: {
        loadListItem () {
            axios.get(`/api/application/`+this.projectId)
                .then(response => {
                console.log('response', response)
                this.item = response.data;
            })
        }
    }
}
</script>

<style>
.breadcrumbs {
    color: #009A96;
}

.breadcrumbs a {
    color: #009A96;
    text-decoration: underline;
}

.project {
    text-align: left;
    color: #4e5861;
}

.itemField {
    width: 80%;
    border-bottom: 1px solid #d1d1d1;
    margin: 10px auto;
    padding-bottom: 8px;
    font-size: 15px;
}

.fieldName {
    display: inline-block;
    width: 50%;
    height: 100%;
    font-weight: 600;
    padding-right: 10px;
}
</style>