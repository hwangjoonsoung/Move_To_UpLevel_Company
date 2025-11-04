package me.mtuc.conference.conf.enums;

import org.springframework.stereotype.Component;

public enum PayMethod {
    ONLINE_CARD, //신용카드 결제 (온라인)
    OFFLINE_CARD, //신용카드 결제 (오프라인)
    BANK_TRANSFER, //무통장 입금
    REALTIME_TRANSFER //실시간 계좌이체
}
