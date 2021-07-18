# cleanclaims

This plugin allow you to clean abandoned [lands](https://www.spigotmc.org/resources/lands-land-claim-plugin-grief-prevention-protection-gui-management-nations-wars-1-17-support.53313/)

## Requirements 
* [lands](https://www.spigotmc.org/resources/lands-land-claim-plugin-grief-prevention-protection-gui-management-nations-wars-1-17-support.53313/) 5.12.1
* Java 1.8 or higher
* [spigot](https://www.spigotmc.org/) 1.16

## Configuration

```yml
criteria:
  # If bank balance and cashback should be send to the owner when a land is remove
  send-bank-balance-and-cashback-to-owner-when-land-deleted: false
  log-sent-to-player-that-started-cleaning: true
  log-sent-to-console: true
  log-format: '&7[&ccleanclaims&7] &f- %s'
  # If the land own strictly more than this amount it won't be removed
  max-number-of-chunks: 2
  # If one of the trusted member (including the owner) has join the server in the latest seconds of the amount defined here the land won't be removed
  min-seconds-with-last-join: 2628000
```

## Commands

`/cleanclaims` display the help

`/cleanclaims retrieve-queue` Iterate over all lands to know which one should be removed and add them in a queue *(⚠️ this operation is done asynchronous and should not lag the server)*

`/cleanclaims check-queue` Once retrieve-queue has been done, display the list of all lands that are in the queue *(⚠️ might display many entries depending on how many lands match criteria)*

`/cleanclaims clean-queue` Once retrieve-queue has been done, delete all the lands in the queue *(⚠️ this operation is done asynchronous but might lag the server depending on how lands handle deletion)*

`/cleanclaims retrieve-queue-and-clean` Command retrieve-queue and clean-queue in one *(⚠️ this operation is done asynchronous but might lag the server depending on how lands handle deletion)*
