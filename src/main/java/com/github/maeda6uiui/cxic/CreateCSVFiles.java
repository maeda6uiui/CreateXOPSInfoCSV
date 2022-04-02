package com.github.maeda6uiui.cxic;

import com.github.dabasan.jxm.properties.character.Character;
import com.github.dabasan.jxm.properties.weapon.Weapon;
import com.github.dabasan.jxm.properties.xops.EXEManipulator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class CreateCSVFiles {
    public static void main(String[] args){
        new CreateCSVFiles();
    }

    public CreateCSVFiles(){
        try{
            System.out.println("処理を開始します");

            var manipulator=new EXEManipulator("./xops0975t.exe");

            outputWeaponInfo(manipulator);
            outputCharacterInfo(manipulator);

            System.out.println("処理が完了しました");
        }
        catch(IOException e){
            e.printStackTrace();
            return;
        }
    }

    private void outputWeaponInfo(EXEManipulator manipulator) throws IOException{
        Weapon[] weapons=manipulator.getWeapons();

        var sb=new StringBuilder();

        //ヘッダ
        sb.append("名前,");
        sb.append("モデル,");
        sb.append("テクスチャ,");
        sb.append("攻撃力,");
        sb.append("貫通力,");
        sb.append("発射間隔,");
        sb.append("弾速,");
        sb.append("装弾数,");
        sb.append("リロード時間,");
        sb.append("反動,");
        sb.append("弾道誤差最小値,");
        sb.append("弾道誤差最大値,");
        sb.append("モデル位置X,");
        sb.append("モデル位置Y,");
        sb.append("モデル位置Z,");
        sb.append("マズルフラッシュ位置X,");
        sb.append("マズルフラッシュ位置Y,");
        sb.append("マズルフラッシュ位置Z,");
        sb.append("薬莢排出位置X,");
        sb.append("薬莢排出位置Y,");
        sb.append("薬莢排出位置Z,");
        sb.append("薬莢排出速度X,");
        sb.append("薬莢排出速度Y,");
        sb.append("連射可能,");
        sb.append("スコープ種類,");
        sb.append("モデル倍率,");
        sb.append("銃声ID,");
        sb.append("銃声音量,");
        sb.append("サイレンサー,");
        sb.append("構え方");
        sb.append("\n");

        //データ
        for(var weapon:weapons){
            sb.append(weapon.name+",");
            sb.append(weapon.model+",");
            sb.append(weapon.texture+",");
            sb.append(weapon.attacks+",");
            sb.append(weapon.penetration+",");
            sb.append(weapon.blazings+",");
            sb.append(weapon.speed+",");
            sb.append(weapon.nbsMax+",");
            sb.append(weapon.reloads+",");
            sb.append(weapon.reaction+",");
            sb.append(weapon.errorRangeMin+",");
            sb.append(weapon.errorRangeMax+",");
            sb.append(weapon.modelPositionX+",");
            sb.append(weapon.modelPositionY+",");
            sb.append(weapon.modelPositionZ+",");
            sb.append(weapon.flashPositionX+",");
            sb.append(weapon.flashPositionY+",");
            sb.append(weapon.flashPositionZ+",");
            sb.append(weapon.yakkyouPositionX+",");
            sb.append(weapon.yakkyouPositionY+",");
            sb.append(weapon.yakkyouPositionZ+",");
            sb.append(weapon.yakkyouSpeedX+",");
            sb.append(weapon.yakkyouSpeedY+",");
            sb.append(weapon.blazingMode+",");
            sb.append(String.format("%d (%s),",weapon.scopeMode.ordinal(),weapon.scopeMode));
            sb.append(weapon.size+",");
            sb.append(weapon.soundID+",");
            sb.append(weapon.soundVolume+",");
            sb.append(weapon.silencer+",");
            sb.append(String.format("%d (%s)",weapon.weaponP.ordinal(),weapon.weaponP));
            sb.append("\n");
        }

        var lines= Arrays.asList(sb.toString().split("\n"));
        Files.write(Paths.get("./weapons.csv"),lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE_NEW);
    }

    private void outputCharacterInfo(EXEManipulator manipulator) throws IOException{
        Character[] characters= manipulator.getCharacters();

        var sb=new StringBuilder();

        //ヘッダ
        sb.append("テクスチャ,");
        sb.append("モデル,");
        sb.append("体力,");
        sb.append("強さ,");
        sb.append("武器1,");
        sb.append("武器2,");
        sb.append("キャラクターの種類");
        sb.append("\n");

        //データ
        for(var character:characters){
            sb.append(String.format("%d (%s),",character.texture.ordinal(),character.texture));
            sb.append(String.format("%d (%s),",character.model.ordinal(),character.model));
            sb.append(character.hp+",");
            sb.append(String.format("%d (%s),",character.aiLevel.ordinal(),character.aiLevel));
            sb.append(character.weapons.get(0)+",");
            sb.append(character.weapons.get(1)+",");
            sb.append(String.format("%d (%s)",character.type.ordinal(),character.type));
            sb.append("\n");
        }

        var lines= Arrays.asList(sb.toString().split("\n"));
        Files.write(Paths.get("./characters.csv"),lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE_NEW);
    }
}
