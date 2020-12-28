#include<stdio.h>
#include<stdlib.h>
#include<locale.h>

int main () {
    setlocale(LC_ALL, "Portuguese");
    printf("O Elevador está a atualizar o Starnet-CDC Espere alguns momentos.");
    printf("O Elevador estás a baixar a atualização mais recente do github");
    system("wget https://github.com/FelipeAlafy/stanet-cdc/releases/latest/download/Starnet-CDC.zip");
    printf("O Elevador estás a deletar alguns arquivos.");
    system("del Starnet-CDC /Q");
    system("rmdir Starnet-CDC /Q");
    printf("O Elevador está a extrair o arquivo Starnet-CDC");
    system("unzip Starnet-CDC.zip");
    printf("O Elevador estás a remover os resíduos da instalação.\n");
    system("del Starnet-CDC.zip /Q");
    printf("\033[34mO Elevador terminou a instalação clique em Enter para que o Elevador feche.\033[m\n\033[31mNão esqueça de abrir manualmente o Programa.\nSe quiser criar um icone na área de trabalho\nentre na pasta \"Starnet-CDC\" e depois novamente em \"Starnet-CDC\",\nAgora clique com o botão direito e enviar para área de trabalho no arquivo \"starnet-cdc.jar\"\033[m\n\n");
    system("pause");
    return 0;
}
