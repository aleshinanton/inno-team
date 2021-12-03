package ru.jat.innoteam.util;

import java.util.Random;

public class PhraseUtil {
    private static final Random RANDOM = new Random();

    private static final String[] PHRASES = new String[]{
            "Разнообразный и богатый опыт говорит нам, что постоянный количественный рост и сфера нашей активности прекрасно подходит для реализации инновационных методов управления процессами.",
            "С учётом сложившейся международной обстановки, сложившаяся структура организации требует от нас анализа системы обучения кадров, соответствующей насущным потребностям.",
            "Но глубокий уровень погружения требует от нас анализа прогресса профессионального сообщества.",
            "Учитывая ключевые сценарии поведения, экономическая повестка сегодняшнего дня позволяет выполнить важные задания по разработке новых предложений.",
            "В своём стремлении улучшить пользовательский опыт мы упускаем, что тщательные исследования конкурентов объединены в целые кластеры себе подобных.",
            "Прежде всего, высокотехнологичная концепция общественного уклада говорит о возможностях поэтапного и последовательного развития общества.",
            "Значимость этих проблем настолько очевидна, что экономическая повестка сегодняшнего дня способствует повышению качества направлений прогрессивного развития.",
            "В своём стремлении повысить качество жизни, они забывают, что социально-экономическое развитие однозначно фиксирует необходимость своевременного выполнения сверхзадачи.",
            "Учитывая ключевые сценарии поведения, новая модель организационной деятельности предопределяет высокую востребованность соответствующих условий активизации.",
            "Предварительные выводы неутешительны: современная методология разработки требует определения и уточнения распределения внутренних резервов и ресурсов.",
            "В целом, конечно, убеждённость некоторых оппонентов в значительной степени обусловливает важность экономической целесообразности принимаемых решений.",
            "С учётом сложившейся международной обстановки, повышение уровня гражданского сознания требует от нас анализа направлений прогрессивного развития.",
            "В целом, конечно, сложившаяся структура организации однозначно определяет каждого участника как способного принимать собственные решения касаемо поставленных обществом задач.",
            "С другой стороны, сплочённость команды профессионалов создаёт предпосылки для благоприятных перспектив.",
            "Учитывая ключевые сценарии поведения, новая модель организационной деятельности предопределяет высокую востребованность глубокомысленных рассуждений.",
            "Идейные соображения высшего порядка, а также постоянный количественный рост и сфера нашей активности требует определения и уточнения благоприятных перспектив.",
            "Социально-экономическое развитие позволяет оценить значение поэтапного и последовательного развития общества.",
            "Равным образом, новая модель организационной деятельности предоставляет широкие возможности для новых предложений!",
            "Есть над чем задуматься: независимые государства указаны как претенденты на роль ключевых факторов.",
            "И нет сомнений, что базовые сценарии поведения пользователей освещают чрезвычайно интересные особенности картины в целом, однако конкретные выводы, разумеется, разоблачены.",
            "Есть над чем задуматься: непосредственные участники технического прогресса неоднозначны и будут заблокированы в рамках своих собственных рациональных ограничений.",
            "Каждый из нас понимает очевидную вещь: перспективное планирование требует анализа экспериментов, поражающих по своей масштабности и грандиозности.",
            "Имеется спорная точка зрения, гласящая примерно следующее: действия представителей оппозиции могут быть описаны максимально подробно.",
            "Сложно сказать, почему диаграммы связей являются только методом политического участия и заблокированы в рамках своих собственных рациональных ограничений.",
            "Равным образом, консультация с широким активом обеспечивает актуальность распределения внутренних резервов и ресурсов.",
            "Разнообразный и богатый опыт говорит нам, что реализация намеченных плановых заданий представляет собой интересный эксперимент проверки системы обучения кадров, соответствующей насущным потребностям.",
            "Идейные соображения высшего порядка, а также сложившаяся структура организации играет определяющее значение для позиций, занимаемых участниками в отношении поставленных задач.",
            "И нет сомнений, что активно развивающиеся страны третьего мира призывают нас к новым свершениям, которые, в свою очередь, должны быть своевременно верифицированы!",
            "Как принято считать, непосредственные участники технического прогресса, вне зависимости от их уровня, должны быть в равной степени предоставлены сами себе.",
            "Лишь многие известные личности ограничены исключительно образом мышления.",
            "Сложно сказать, почему представители современных социальных резервов неоднозначны и будут функционально разнесены на независимые элементы.",
            "Также как повышение уровня гражданского сознания является качественно новой ступенью существующих финансовых и административных условий.",
            "Учитывая ключевые сценарии поведения, убеждённость некоторых оппонентов обеспечивает широкому кругу (специалистов) участие в формировании инновационных методов управления процессами.",
            "Равным образом, укрепление и развитие внутренней структуры влечет за собой процесс внедрения и модернизации соответствующих условий активизации.",
            "Мы вынуждены отталкиваться от того, что базовый вектор развития предполагает независимые способы реализации соответствующих условий активизации.",
            "В частности, выбранный нами инновационный путь прекрасно подходит для реализации благоприятных перспектив.",
            "Но акционеры крупнейших компаний объявлены нарушающими общечеловеческие нормы этики и морали.",
            "Задача организации, в особенности же внедрение современных методик выявляет срочную потребность благоприятных перспектив.",
            "В целом, конечно, семантический разбор внешних противодействий напрямую зависит от распределения внутренних резервов и ресурсов.",
            "Банальные, но неопровержимые выводы, а также диаграммы связей и по сей день остаются уделом либералов, которые жаждут быть в равной степени предоставлены сами себе.",
            "Сложно сказать, почему базовые сценарии поведения пользователей могут быть объявлены нарушающими общечеловеческие нормы этики и морали.",
            "Господа, повышение уровня гражданского сознания предоставляет широкие возможности для анализа существующих паттернов поведения.",
            "Банальные, но неопровержимые выводы, а также предприниматели в сети интернет неоднозначны и будут функционально разнесены на независимые элементы.",
            "Как уже неоднократно упомянуто, тщательные исследования конкурентов неоднозначны и будут призваны к ответу.",
            "Принимая во внимание показатели успешности, реализация намеченных плановых заданий предоставляет широкие возможности для первоочередных требований.",
            "Современные технологии достигли такого уровня, что постоянный количественный рост и сфера нашей активности требует анализа новых предложений."
    };

    public static String getPhrase() {
        int rnd = RANDOM.nextInt(PHRASES.length);
        return PHRASES[rnd];
    }
}
