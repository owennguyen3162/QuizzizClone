package eu.tutorials.quizzizclone

import eu.tutorials.quizzizclone.model.Question

object Constants {
    const val CODE: String = "USERNAME"
    const val CORRECT_ANSWER: String = "CORRECT_ANSWER"
    fun getQuestions(): ArrayList<Question> {
        val questions = ArrayList<Question>()
        val questionOne = Question(
            1,
            "Đâu là một loại hình chợ tạm tự phát thường xuất hiện trong các khu dân cư?",
            "Ếch",
            "Cóc",
            "Thằn lằn",
            "Nhái",
            2,
        )
        val questionTwo = Question(
            2,
            "Đâu là tên một loại chợ?",
            "Ếch",
            "Nhái",
            "Thằn lằn",
            "Cóc",
            4,
        )
        val questionThree = Question(
            3,
            "Đâu là tên một bãi biển đẹp ở Quảng Bình?",
            "Đá Lăn",
            "Đá Chạy",
            "Đá Nhảy",
            "Đá Bò",
            3,
        )
        val questionFour = Question(
            4,
            "Chiến trường Đắk Tô - Tân Cảnh, nơi diễn ra chiến thắng vang đội năm 1972, nay thuộc địa bàn tỉnh nào ở Tây Nguyên?",
            "Kon Tum",
            "Đắk Lắk",
            "Gia Lai",
            "Đắk Nông",
            3,
        )
        val questionFive = Question(
            5,
            "Tượng đài Chiến thắng Điện Biên Phủ được dựng trên ngọn đồi nào?",
            "D1",
            "C1",
            "A1",
            "E1",
            1,
        )
        val questionSix = Question(
            6,
            "Màu chủ đạo của tờ tiền polymer mệnh giá 500.000 đồng là màu nào?",
            "Đỏ",
            "Xanh",
            "Tím",
            "Vàng",
            2,
        )
        val questionSeven = Question(
            7,
            "Bảo tàng Hồ Chí Minh được thiết kế theo dáng một loài hoa nào?",
            "Hoa sen",
            "Hoa hướng dương",
            "Hoa hồng",
            "Hoa đào",
            1,
        )
        questions.add(questionOne);
        questions.add(questionTwo);
        questions.add(questionThree);
        questions.add(questionFour);
        questions.add(questionFive);
        questions.add(questionSix);
        questions.add(questionSeven);
        return questions
    }
}