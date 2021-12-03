import telebot
import random
import pandas as pd
import numpy as np

from anekdots import anekdots

########################################################################

bot_name = 'Транспортные инновации Москвы'
bot_url = 't.me/ftimoscow_bot'
token = '2129563093:AAGKgTUH7oTcpSuvdlV3M-x-JyKNc3V5apI'

########################################################################


bot_message_start = """👋Привет, транспортный инноватор!

🤖Я бот - помогу тебе подать заявку на грант и автоматически подобрать тебе "Технологическое направление" и проверить насколько твоя заявка вписывается в тренд инноваций!

🔎Технологические направления представлены на сайте https://ftim.ru/

❗️Обрати внимание, что ты можешь объединяться с другими стартаперами с целью оптимизации и улучшения качества решения.

💭Чат для участников:
https://t.me/joinchat/BafRKh1yNq5QWACjurPvBw"""



menu_text_istrend = '📈Проверить трендовость'
menu_text_tech_catalog = '⚙️Технологические направления'

menu_text_application = '📝Подать заявку'
menu_text_application_status = '❓Статус заявки'

menu_text_tellastory = '😉Транспортный анекдот'



catalog_groups_list = [
    '🚅Доступный и комфортный городской транспорт',
    '🚀Новые виды мобильности',
    '🩸Безопасность дорожного движения',
    '🌳Здоровые улицы и экология',
    '📲Цифровые технологии в транспорте'
]

########################################################################

class Keyboard:
    def __init__(self, bot):
        self.bot = bot

    def menu_main(self, message):
        user_markup = telebot.types.ReplyKeyboardMarkup(True, False)
        user_markup.row(menu_text_istrend, menu_text_tech_catalog)
        user_markup.row(menu_text_application, menu_text_application_status)
        user_markup.row(menu_text_tellastory)
        # self.bot.send_message(message.from_user.id, 'Выберите пункт меню:', reply_markup=user_markup)
        return user_markup

    def menu_catalog_groups(self, message, catalog_groups):
        groups_per_row = 1  # кол-во групп на одну строку
        groups_count = len(catalog_groups_list)
        user_markup = telebot.types.ReplyKeyboardMarkup(True, False)
        for k in range(groups_count // groups_per_row + groups_count % groups_per_row):
            user_markup.row(*[catalog_groups_list[k * groups_per_row + n] for n in range(groups_per_row)])
        return user_markup


########################################################################

bot = telebot.TeleBot(token)


########################################################################

@bot.message_handler(commands=['start'])
def start_message(message):
    bot.send_message(message.chat.id, bot_message_start, reply_markup=Keyboard(bot).menu_main(message))


#     with open('usama.jpg', 'rb') as photo:
#         bot.send_photo(message.chat.id, photo, caption=anekdots[24], reply_markup=Keyboard(bot).menu_main(message))

@bot.message_handler(commands=['catalog'])
def catalog_message(message):
    bot.send_message(message.chat.id, 'Какое тех направление вам подходит?',
                     reply_markup=Keyboard(bot).menu_catalog_groups(message, catalog_groups_list))


@bot.message_handler(commands=['apply'])
def apply_message(message):
    bot.send_message(message.chat.id, 'Это прототип! Функционал еще нужно реализовать.',
                     reply_markup=Keyboard(bot).menu_main(message))


@bot.message_handler(commands=['status'])
def status_message(message):
    bot.send_message(message.chat.id, 'Это прототип! Функционал еще нужно реализовать.',
                     reply_markup=Keyboard(bot).menu_main(message))


@bot.message_handler(commands=['trend'])
def trend_message(message):
    bot.send_message(message.chat.id, 'Это прототип! Функционал еще нужно реализовать.',
                     reply_markup=Keyboard(bot).menu_main(message))


@bot.message_handler(commands=['support'])
def support_message(message):
    bot.send_message(message.chat.id, 'Это прототип! Функционал еще нужно реализовать.',
                     reply_markup=Keyboard(bot).menu_main(message))


@bot.message_handler(commands=['tellastory'])
def tellastory_message(message):
    bot_message_tellastory = random.choice(anekdots)
    if bot_message_tellastory == 'anekdot.mp4':
        with open('anekdot.mp4', 'rb') as video:
            bot.send_video(message.chat.id, video, reply_markup=Keyboard(bot).menu_main(message))
    elif bot_message_tellastory == anekdots[24]:
        with open('usama.jpg', 'rb') as photo:
            bot.send_photo(message.chat.id, photo, caption=anekdots[24], reply_markup=Keyboard(bot).menu_main(message))
    else:
        bot.send_message(message.chat.id, bot_message_tellastory, reply_markup=Keyboard(bot).menu_main(message))


@bot.message_handler(content_types='text')
def message_reply(message):
    if message.text == menu_text_tellastory:
        bot_message_tellastory = random.choice(anekdots)
        if bot_message_tellastory == 'anekdot.mp4':
            with open('anekdot.mp4', 'rb') as video:
                bot.send_video(message.chat.id, video, reply_markup=Keyboard(bot).menu_main(message))
        elif bot_message_tellastory == anekdots[24]:
            with open('usama.jpg', 'rb') as photo:
                bot.send_photo(message.chat.id, photo, caption=anekdots[24],
                               reply_markup=Keyboard(bot).menu_main(message))
        else:
            bot.send_message(message.chat.id, bot_message_tellastory, reply_markup=Keyboard(bot).menu_main(message))
    elif message.text in catalog_groups_list:
        bot.send_message(message.chat.id, 'Это прототип! Функционал еще нужно реализовать.',
                         reply_markup=Keyboard(bot).menu_main(message))
    elif message.text == menu_text_tech_catalog:
        bot.send_message(message.chat.id, 'Какое тех направление вам подходит?',
                         reply_markup=Keyboard(bot).menu_catalog_groups(message, catalog_groups_list))
    elif message.text == menu_text_application_status:
        bot.send_message(message.chat.id, 'Это прототип! Функционал еще нужно реализовать.',
                         reply_markup=Keyboard(bot).menu_main(message))
    elif message.text in menu_text_application:
        bot.send_message(message.chat.id, 'Это прототип! Функционал еще нужно реализовать.',
                         reply_markup=Keyboard(bot).menu_main(message))
    elif message.text == menu_text_istrend:
        bot.send_message(message.chat.id, 'Это прототип! Функционал еще нужно реализовать.',
                         reply_markup=Keyboard(bot).menu_main(message))


########################################################################


if __name__ == '__main__':
    bot.polling()
