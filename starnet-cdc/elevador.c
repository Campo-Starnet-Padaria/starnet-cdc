#include<stdio.h>
#include<stdlib.h>
#include<locale.h>

int main () {
    setlocale(LC_ALL, "Portuguese");
    printf("O Elevador est� a atualizar o Starnet-CDC Espere alguns momentos.");
    printf("O Elevador est�s a baixar a atualiza��o mais recente do github");
    system("wget https://github.com/FelipeAlafy/stanet-cdc/releases/latest/download/Starnet-CDC.zip");
    printf("O Elevador est�s a deletar alguns arquivos.");
    system("del Starnet-CDC /Q");
    system("rmdir Starnet-CDC /Q");
    printf("O Elevador est� a extrair o arquivo Starnet-CDC");
    system("unzip Starnet-CDC.zip");
    printf("O Elevador est�s a remover os res�duos da instala��o.\n");
    system("del Starnet-CDC.zip /Q");
    printf("\033[34mO Elevador terminou a instala��o clique em Enter para que o Elevador feche.\033[m\n\033[31mN�o esque�a de abrir manualmente o Programa.\nSe quiser criar um icone na �rea de trabalho\nentre na pasta \"Starnet-CDC\" e depois novamente em \"Starnet-CDC\",\nAgora clique com o bot�o direito e enviar para �rea de trabalho no arquivo \"starnet-cdc.jar\"\033[m\n\n");
    system("pause");
    return 0;
}
