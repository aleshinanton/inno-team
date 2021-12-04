<template>
  <div class="task">
        <p class="breadcrumbs"><a href="/tasks">Реестр решений</a> &#8594; {{taskId}}</p>
        <div class="itemField">
            <div class="fieldName">
                <p>Что болит?</p>
                <p class="small">Опишите своими словами существующую в организации проблему. Можно сформулировать проблему в форму задачи</p>
            </div> 
            <div class="projectDataValue">
                <p>{{item.issue}}</p>
            </div>
        </div>
        <!-- <div class="itemField"><div class="fieldName">Организация транспортного комплекса Москвы:</div> <div class="projectDataValue">{{item.project.orgName}}</div></div>
        <div class="itemField"><div class="fieldName">Участник программы пилотирования «Транспортные инновации Москвы»:</div> <div class="projectDataValue">{{item.project.participant}}</div></div>
        <div class="itemField"><div class="fieldName">Руководитель проекта:</div> <div class="projectDataValue">{{item.project.projectManager}}</div></div>
        <div class="itemField"><div class="fieldName">Координатор от участника программы пилотирования «Транспортные инновации Москвы»:</div> <div class="projectDataValue">{{item.project.programCoordinator}}</div></div>
        <div class="itemField"><div class="fieldName">Координатор от организации транспортного комплекса Москвы:</div> <div class="projectDataValue">{{item.project.orgCoordinator}}</div></div>
        <div class="itemField"><div class="fieldName">Краткое описание продукта:</div> <div class="projectDataValue">{{item.project.description}}</div></div>
        <div class="itemField"><div class="fieldName">Сроки реализации проекта:</div> <div class="projectDataValue">{{item.project.term}}</div></div>
        <div class="itemField"><span class="fieldName">Tеги:</span><span v-for="item in item.tags" :key="item"><span class="tag">{{item}}</span></span></div>
        <div class="context"><span class="contextName">Контекст и потребности:</span> <div class="contextData">{{item.project.context}}</div></div> -->

  </div>
</template>

<script>
import axios from 'axios'
export default {
    props: ['taskId'],
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
            axios.get(`/api/issue/`+this.taskId)
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

.small {
    font-weight: 400;
    font-size: 12px;
}

.task {
    text-align: left;
    color: #4e5861;
}

.itemField {
    width: 99%;
    border-bottom: 1px solid #d1d1d1;
    margin: 10px auto;
    padding-bottom: 8px;
    font-size: 15px;
    display: flex;
}

.fieldName {
    display: inline-block;
    width: 50%;
    height: 100%;
    font-weight: 600;
    padding-right: 10px;
}
.projectDataValue {
    width: 50%;
}

p.fieldName {
    margin: 0;
    margin-top: 25px;
}
</style>