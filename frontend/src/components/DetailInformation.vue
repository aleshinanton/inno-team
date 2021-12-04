<template>
    <div class="project">
        <p class="breadcrumbs"><a href="/projects">Реестр решений</a> &#8594; {{projectId}}</p>
        <div class="itemField"><div class="fieldName">Наименование проекта:</div> <div class="projectDataValue">{{item.project.name}}</div></div>
        <div class="itemField"><div class="fieldName">Организация транспортного комплекса Москвы:</div> <div class="projectDataValue">{{item.project.orgName}}</div></div>
        <div class="itemField"><div class="fieldName">Участник программы пилотирования «Транспортные инновации Москвы»:</div> <div class="projectDataValue">{{item.project.participant}}</div></div>
        <div class="itemField"><div class="fieldName">Руководитель проекта:</div> <div class="projectDataValue">{{item.project.projectManager}}</div></div>
        <div class="itemField"><div class="fieldName">Координатор от участника программы пилотирования «Транспортные инновации Москвы»:</div> <div class="projectDataValue">{{item.project.programCoordinator}}</div></div>
        <div class="itemField"><div class="fieldName">Координатор от организации транспортного комплекса Москвы:</div> <div class="projectDataValue">{{item.project.orgCoordinator}}</div></div>
        <div class="itemField"><div class="fieldName">Краткое описание продукта:</div> <div class="projectDataValue">{{item.project.description}}</div></div>
        <div class="itemField"><div class="fieldName">Сроки реализации проекта:</div> <div class="projectDataValue">{{item.project.term}}</div></div>
        <div class="itemField"><span class="fieldName">Tеги:</span><span v-for="item in item.tags" :key="item"><span class="tag">{{item}}</span></span></div>
        <div class="context"><span class="contextName">Контекст и потребности:</span> <div class="contextData">{{item.project.context}}</div></div>


        <p class="fieldName">Ожидаемые эффекты проекта</p>
        <table>
                <tr>
                    <td class="numberField">№</td>
                    <td class="nameField">Наименование</td>
                    <td class="valueField">Значение показателя</td>
                </tr>
                <tr v-for="(row, index) in item.project.expectedProjectEffects" :key="row.id">
                    <td>{{index + 1}}</td>
                    <td>{{row.name}}</td>
                    <td>{{row.indicator}}</td>
                </tr>
        </table>


        <p class="fieldName">Этапы проекта</p>
        <table>
                <tr>
                    <td class="numberField">№</td>
                    <td class="nameField">Наименование</td>
                    <td class="valueField">Ожидаемая дата завершения</td>
                </tr>
                <tr v-for="(row, index) in item.project.projectStages" :key="row.stage">
                    <td>{{index + 1}}</td>
                    <td>{{row.stage}}</td>
                    <td>{{row.term}}</td>
                </tr>
        </table>

        <p class="fieldName">Команда проекта</p>
        <table>
                <tr>
                    <td class="numberField">№</td>
                    <td>ФИО</td>
                    <td>Должность/Организация</td>
                    <td>Контакты</td>
                </tr>
                <tr v-for="(row, index) in item.project.projectMembers" :key="row.id">
                    <td>{{index + 1}}</td>
                    <td>{{row.fullName}}</td>
                    <td>{{row.position}} / {{row.organization}}</td>
                    <td>{{row.contact}}</td>
                </tr>
        </table>

        <p class="fieldName">Бюджет проекта</p>
        <table>
                <tr>
                    <td class="numberField">№</td>
                    <td class="nameField">Наименование</td>
                    <td class="valueField">Значение показателя</td>
                </tr>
                <tr v-for="(row, index) in item.project.expenseItems" :key="row.id">
                    <td>{{index + 1}}</td>
                    <td>{{row.name}}</td>
                    <td>{{row.indicator}}</td>
                </tr>
        </table>

        <p class="fieldName">Статус проекта</p>
        <table>
                <tr>
                    <td>Статус</td>
                    <td>Ожидаемые результаты и мероприятия</td>
                    <td>Дата</td>
                </tr>
                <tr v-for="(row) in item.project.projectStatuses" :key="row.term">
                    <td>{{row.status}}</td>
                    <td>{{row.expectedResults}}</td>
                    <td>{{row.term}}</td>
                </tr>
        </table>

        <p class="fieldName">Собрания по проекту</p>
        <table>
                <tr>
                    <td>Комментарий</td>
                    <td>Дата</td>
                </tr>
                <tr v-for="(row) in item.project.projectMeetings" :key="row.comment">
                    <td>{{row.comment}}</td>
                    <td>{{row.date}}</td>
                </tr>
        </table>

        <p class="fieldName">Материалы проекта</p>
        <table>
                <tr>
                    <td>Наименование</td>
                    <td>Ссылка</td>
                </tr>
                <tr v-for="(row) in item.project.projectMaterials" :key="row.url">
                    <td>{{row.name}}</td>
                    <td>{{row.url}}</td>
                </tr>
        </table>
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

<style scoped>
table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 10px;
    margin-bottom: 5px;
}

tr:first-child {
    font-weight: 600;
}

td {
    border: 1px solid #009A96;
    padding: 10px;
}

.numberField {
    width: 5%;
}

.nameField {
    width: 60%;
}

.valueField {
    width:60%;
}

caption {
    text-align: left;
}

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

.tag {
  background-color: #009A96;
  padding: 2px 10px;
  color: #fff;
  border-radius: 10px;
  margin-left: 10px;
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

.contextName {
    display: inline-block;
    font-weight: 600;
    width: 100%;
    margin-top: 15px;
    margin-bottom: 10px;
}

.contextData {
    border: 1px solid #009A96;
    border-radius: 5px;
    min-height: 80px;
    padding: 5px;
}
</style>