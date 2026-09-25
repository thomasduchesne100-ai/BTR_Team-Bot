package com.btr.teambot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main extends ListenerAdapter {

    public static void main(String[] args) throws Exception {
        String token = System.getenv("DISCORD_TOKEN");

        if (token == null || token.isBlank()) {
            System.err.println("ERREUR: la variable DISCORD_TOKEN n'est pas configurée.");
            System.err.println("Windows PowerShell: $env:DISCORD_TOKEN=\"TON_TOKEN\"");
            return;
        }

        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new Main())
                .build();

        jda.awaitReady();
        System.out.println("BTR Team Bot est connecté !");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.isFromGuild()) return;
        if (event.getAuthor().isBot()) return;

        String content = event.getMessage().getContentRaw().trim();

        if (content.equalsIgnoreCase("!setup")) {
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("🏆 SYSTÈME DE CLASSEMENT")
                    .setDescription("Clique sur le bouton ci-dessous pour créer un classement à partir d'un rôle Discord.")
                    .setColor(new Color(88, 101, 242));

            event.getChannel().sendMessageEmbeds(embed.build())
                    .setActionRow(Button.primary("setup_ranking", "🏆 Mettre Un Classement"))
                    .queue();
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (!event.getComponentId().equals("setup_ranking")) return;

        List<Role> roles = new ArrayList<>(event.getGuild().getRoles());
        roles.removeIf(r -> r.isManaged() || r.isPublicRole());

        roles.sort(Comparator.comparing(Role::getPosition).reversed());

        if (roles.isEmpty()) {
            event.reply("❌ Aucun rôle utilisable n'a été trouvé.").setEphemeral(true).queue();
            return;
        }

        StringSelectMenu.Builder menu = StringSelectMenu.create("ranking_role")
                .setPlaceholder("Choisir la team / le rôle");

        int max = Math.min(25, roles.size());
        for (int i = 0; i < max; i++) {
            Role role = roles.get(i);
            menu.addOption(role.getName(), role.getId());
        }

        event.reply("👥 **Choisis le rôle de la team à classer :**")
                .addActionRow(menu.build())
                .setEphemeral(true)
                .queue();
    }

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        if (!event.getComponentId().equals("ranking_role")) return;

        String roleId = event.getValues().get(0);
        Role role = event.getGuild().getRoleById(roleId);

        if (role == null) {
            event.reply("❌ Rôle introuvable.").setEphemeral(true).queue();
            return;
        }

        List<String> players = new ArrayList<>();
        event.getGuild().findMembersWithRoles(role).forEach(member ->
                players.add(member.getEffectiveName()));

        players.sort(String.CASE_INSENSITIVE_ORDER);

        StringBuilder description = new StringBuilder();
        if (players.isEmpty()) {
            description.append("Aucun joueur possède actuellement ce rôle.");
        } else {
            String[] medals = {"🥇", "🥈", "🥉"};
            for (int i = 0; i < players.size(); i++) {
                String prefix = i < 3 ? medals[i] : (i + 1) + "️⃣";
                description.append(prefix).append(" ").append(players.get(i)).append("\n");
            }
        }

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("🏆 CLASSEMENT — " + role.getName())
                .setDescription(description.toString())
                .setColor(role.getColor() != null ? role.getColor() : new Color(88, 101, 242))
                .setFooter("Les membres sont récupérés automatiquement depuis le rôle Discord.");

        event.replyEmbeds(embed.build())
                .setEphemeral(false)
                .queue();
    }
}
